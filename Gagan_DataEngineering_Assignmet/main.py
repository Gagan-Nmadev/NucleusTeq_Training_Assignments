from my_processor.core import MemberDataProcessor


raw_members = [
    {
        "name": "John Doe",
        "email": "john.doe@example.com",
        "phone": "555-0101"
    },
    {
        "name": "Jane Smith",
        "email": "jane.smith@example.com",
        "phone": "555-0102"
    },
    {
        "name": "InvalidData",
        "email": "invalid-email",
        "phone": "555-0103"
    },
    {
        "name": "Robert Brown",
        "email": "robert.brown@example.com",
        "phone": "555-0104"
    }
]


def main():

    processor = MemberDataProcessor(raw_members)

    processor.process_members()

    print("\n-----------------------------")
    print(
        f"Summary: {processor.get_member_count()} "
        "members processed successfully."
    )

    print("\nSuccessfully Processed Members:")

    for member in processor.get_all_members():
        print(member)

    print("\nFiltering members using Lambda + filter:")

    john_members = [processor.filter_members_by_name("John Doe"),processor.filter_members_by_name("Jane Smith")]

    for member in john_members:
        print(member)


if __name__ == "__main__":
    main()